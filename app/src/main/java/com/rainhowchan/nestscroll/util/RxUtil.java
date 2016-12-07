package com.rainhowchan.nestscroll.util;

import android.nfc.Tag;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/12/7.
 */

public class RxUtil {

    private static final String TAG="test";

    public static void createObservable(){
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("RainhowChan");
                    subscriber.onNext("Hello World!");
                    subscriber.onNext("Hello World!");
                    subscriber.onCompleted();
                }
            }
        });

        Subscriber<String> subscriber=new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext  "+s);
            }
        };
        observable.subscribe(subscriber);
    }

    public static void createPriint(){
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 10; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext  "+integer);
            }
        });
    }

    public static void createFromCount(){
        Integer[] index={1,2,3,4,5,6,7,8,9};
        Observable.from(index).subscribe(integer -> {
            Log.i(TAG, integer+"----");
        });
    }

    public static void interval() {
//        Integer[] index={1,2,3,4,5,6,7,8,9};
        Observable.interval(1, 1, TimeUnit.SECONDS).subscribe(aLong -> {
            Log.i(TAG, aLong + "----");
        });
    }

    public static void range() {
        Integer[] index={1,2,3,4,5,6,7,8,9};
        Observable.from(index).filter(integer -> integer>4&&integer<8).subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, integer + "----");
            }
        });
    }


}
