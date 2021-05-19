package com.lianhechuangxin.jetpackmvvm.player.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;


import com.lianhechuangxin.jetpackmvvm.player.PlayerManager;

import java.util.Objects;

/**
 * 播放的广播
 * 用于接收 某些改变（系统发出来的信息，断网了），对音乐做出对应操作
 */
public class PlayerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Objects.equals(intent.getAction(), Intent.ACTION_MEDIA_BUTTON)) {
            if (intent.getExtras() == null) {
                return;
            }
            KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
            if (keyEvent == null) {
                return;
            }
            if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
                return;
            }

            switch (keyEvent.getKeyCode()) {
                case KeyEvent.KEYCODE_HEADSETHOOK:
                case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                    PlayerManager.getInstance().togglePlay();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PLAY:
                    PlayerManager.getInstance().playAudio();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PAUSE:
                    PlayerManager.getInstance().pauseAudio();
                    break;
                case KeyEvent.KEYCODE_MEDIA_STOP:
                    PlayerManager.getInstance().clear();
                    break;
                case KeyEvent.KEYCODE_MEDIA_NEXT:
                    PlayerManager.getInstance().playNext();
                    break;
                case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
                    PlayerManager.getInstance().playPrevious();
                    break;
                default:
            }

        } else {

            if (Objects.requireNonNull(intent.getAction()).equals(PlayerService.NOTIFY_PLAY)) {
                PlayerManager.getInstance().playAudio();
            } else if (intent.getAction().equals(PlayerService.NOTIFY_PAUSE)
                    || intent.getAction().equals(android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
                PlayerManager.getInstance().pauseAudio();
            } else if (intent.getAction().equals(PlayerService.NOTIFY_NEXT)) {
                PlayerManager.getInstance().playNext();
            } else if (intent.getAction().equals(PlayerService.NOTIFY_CLOSE)) {
                PlayerManager.getInstance().clear();
            } else if (intent.getAction().equals(PlayerService.NOTIFY_PREVIOUS)) {
                PlayerManager.getInstance().playPrevious();
            }
        }
    }
}
