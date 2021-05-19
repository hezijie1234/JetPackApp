package com.lianhechuangxin.jetpackmvvm.data.bean;


import com.kunminx.player.bean.base.BaseAlbumItem;
import com.kunminx.player.bean.base.BaseArtistItem;
import com.kunminx.player.bean.base.BaseMusicItem;

/**
 * 歌曲 专辑 歌手  本身的实体Bean 对象
 * 被 PlayerManager 使用
 * 被 PlayerService 使用
 * 被 IRemoteRequest接口 使用了
 * 被 IRemoteRequest接口 使用了
 */
public class TestAlbum extends BaseAlbumItem<TestAlbum.TestMusic, TestAlbum.TestArtist> {

    // 专辑 Mid
    private String albumMid;

    public String getAlbumMid() {
        return albumMid;
    }
    public void setAlbumMid(String albumMid) {
        this.albumMid = albumMid;
    }

    // 歌曲 Mid
    public static class TestMusic extends BaseMusicItem<TestArtist> {

        private String songMid;

        public String getSongMid() {
            return songMid;
        }

        public void setSongMid(String songMid) {
            this.songMid = songMid;
        }
    }

    // 歌手相关
    public static class TestArtist extends BaseArtistItem {

        private String birthday;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }
    }
}

