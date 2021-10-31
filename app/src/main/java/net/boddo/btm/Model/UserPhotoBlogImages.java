package net.boddo.btm.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPhotoBlogImages implements Parcelable {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("like")
        @Expose
        private String like;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("matched")
        @Expose
        private String matched;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("action_at")
        @Expose
        private String actionAt;

    protected UserPhotoBlogImages(Parcel in) {
        id = in.readString();
        userId = in.readString();
        photo = in.readString();
        description = in.readString();
        like = in.readString();
        comment = in.readString();
        matched = in.readString();
        status = in.readString();
        createdAt = in.readString();
        actionAt = in.readString();
    }

    public static final Creator<UserPhotoBlogImages> CREATOR = new Creator<UserPhotoBlogImages>() {
        @Override
        public UserPhotoBlogImages createFromParcel(Parcel in) {
            return new UserPhotoBlogImages(in);
        }

        @Override
        public UserPhotoBlogImages[] newArray(int size) {
            return new UserPhotoBlogImages[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMatched() {
        return matched;
    }

    public void setMatched(String matched) {
        this.matched = matched;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getActionAt() {
        return actionAt;
    }

    public void setActionAt(String actionAt) {
        this.actionAt = actionAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userId);
        dest.writeString(photo);
        dest.writeString(description);
        dest.writeString(like);
        dest.writeString(comment);
        dest.writeString(matched);
        dest.writeString(status);
        dest.writeString(createdAt);
        dest.writeString(actionAt);
    }
}
