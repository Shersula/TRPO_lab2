package com.trpo;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

abstract class Writer implements Observer<NativeKeyEvent> {

	protected StringBuilder sBuilder = new StringBuilder();
	
	protected abstract void write(String endMsg);

	@Override
	public void onComplete() {
		write("Complete event!");
	}

	@Override
	public void onError(Throwable ex) {
		write("Exceptiron: " + ex.getLocalizedMessage());
	}

	@Override
	public void onNext(NativeKeyEvent event) {
		sBuilder.append(String.format("%s: %s\n",
				event.getID() == NativeKeyEvent.NATIVE_KEY_PRESSED ? ("Press"):("Release"),
				NativeKeyEvent.getKeyText(event.getKeyCode())));
	}

	@Override
	public void onSubscribe(Disposable arg0)
	{
		sBuilder.append("Writer succes subscribe\n");
	}
}
