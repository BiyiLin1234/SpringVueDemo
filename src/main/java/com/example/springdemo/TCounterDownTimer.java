package com.example.springdemo;

//评测题目: 实现一个倒计时，TCountDownTimer，  SystemClock.elapsedRealtime();

import java.util.Deque;
import java.util.LinkedList;

/**
 * 倒计时工具类
 * @author Biyilin
 * @time 2023/2/2
 */
public class TCounterDownTimer {
    private final Deque<ICallBack> callBackQueue = new LinkedList<>(); // 待通知的回调，倒计时每隔一段时间

    private long mCurrentRemainTimeInMills; // 倒计时剩余的时间，单位毫秒
    private TimerHelper mTimeHelper; // 时间工具
    private Thread mThread;

    // 构造函数
    public TCounterDownTimer() {
    }

    /**
     * 开始倒计时，如果正在倒计时，则会清零并重新开始。
     * @param timeInMills 倒计时时间，单位毫秒
     */
    public void startCountDown(long timeInMills) {
        // 清零上一次
        if (mThread != null && mThread.isAlive()) {
            stopCountDown();
        }
        // 开始本次倒计时
        mThread = new Thread(() -> {
            mTimeHelper = new TimerHelper() {
                @Override
                public void setTotalTime(long tInMills) {
                    // 模拟设置倒计时时间，隔固定时间会回调onTick方法抛出通知。
                    System.out.println(timeInMills);
                }

                @Override
                public void onTick(long t) {
                    if (t == 0) {
                        // 通知结束。
                        for (ICallBack callBack : callBackQueue) {
                            callBack.onFinish();
                        }
                    }
                    // 遍历所有的回调并通知倒计时剩余时间
                    for (ICallBack callBack : callBackQueue) {
                        callBack.onTick(t);
                    }
                }

                // 模拟返回剩余的时间
                @Override
                public long getRemainTime() {
                    return 0;
                }
            };
        });
        mThread.start();
    }

    // 停止倒计时，同时会移除所有的倒计时回调监听。
    public void stopCountDown() {
        mThread.stop();
        removeAllCallBack();
    }

    // 剩余秒数获取接口，单位毫秒
    public long getRemainTime() {
        return SystemClock.elapsedRealtime();
    }

    // 回调通知，可以添加多个。添加和移除应该成对出现。
    public void setCallBack(ICallBack callBack) {
        callBackQueue.add(callBack);
    }

    // 移除回调通知
    public void removeCallBack(ICallBack callBack) {
        if (!callBackQueue.contains(callBack)) {
            // 这里日志工具打印，callback未在队列中被发现的异常情况
        } else {
            callBackQueue.remove(callBack);
        }
    }

    public void removeAllCallBack() {
        callBackQueue.clear();
    }
}

// ==============================================================================================
// ==============================================================================================
// 模拟数据


// 回调通知接口
interface ICallBack {
    // 回调抛出剩余时间
    void onTick(long t);
    // 回调抛出倒计时结束事件
    void onFinish();
}

class SystemClock {
    // 虚拟接口，返回已经消耗/经过的剩余时间
    public static long elapsedRealtime() {
        return 0;
    }
}

interface TimerHelper {
    void setTotalTime(long tInMills);
    void onTick(long tInMills);
    long getRemainTime();
}