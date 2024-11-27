package com.trpo;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import io.reactivex.subjects.PublishSubject;

public class KeyTracker implements Runnable
{
	private final PublishSubject<NativeKeyEvent> pSubject = PublishSubject.create();

	@Override
	public void run()
	{
		try
        {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(new NativeKeyListener()
            {
                @Override
                public void nativeKeyReleased(NativeKeyEvent nativeEvent)
                {
                    pSubject.onNext(nativeEvent);
                }

                @Override
                public void nativeKeyPressed(NativeKeyEvent nativeEvent)
                {
					pSubject.onNext(nativeEvent);

					if(nativeEvent.getKeyCode() == NativeKeyEvent.VC_Z && (nativeEvent.getModifiers() & NativeKeyEvent.CTRL_MASK) != 0)
                    {
                        pSubject.onComplete();
                        try
                        {
                            GlobalScreen.unregisterNativeHook();
                        }
                        catch (NativeHookException e)
                        {
                            pSubject.onError(e);
                        }
                    }
                }
            });
        }
        catch (NativeHookException e)
        {
            pSubject.onError(e);
        }
	}

	public PublishSubject<NativeKeyEvent> getSubject() {
		return pSubject;
	}

}
