package ssm.blog.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by liuxiwen on 2017/1/15.
 */
public class Blogger implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String profile;
    private String nickname;
    private String sign;
    private String imagename;

    public Blogger() {

    }

    public Blogger(Integer id, String username, String password, String profile, String nickname, String sign, String imagename) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.nickname = nickname;
        this.sign = sign;
        this.imagename = imagename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    @Override
    public String toString() {
        return "Blogger{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sign='" + sign + '\'' +
                ", imagename='" + imagename + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blogger blogger = (Blogger) o;
        return Objects.equals(id, blogger.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
