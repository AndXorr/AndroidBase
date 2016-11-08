package net.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 17/8/15.
 */
public class ConfigModel {
    private String message;
    private DataModel data;
    private boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public class DataModel {
        private WalkthroughModel walkthrough;
        private PushMessageChannelModel pubnub_channels;
        private SettingModel setting;
        private VideoStreamDataModel video_streaming;
        private PushTypes push_types;

        public WalkthroughModel getWalkthrough() {
            return walkthrough;
        }

        public void setWalkthrough(WalkthroughModel walkthrough) {
            this.walkthrough = walkthrough;
        }

        public PushMessageChannelModel getPubnub_channels() {
            return pubnub_channels;
        }

        public void setPubnub_channels(PushMessageChannelModel pubnub_channels) {
            this.pubnub_channels = pubnub_channels;
        }

        public SettingModel getSetting() {
            return setting;
        }

        public void setSetting(SettingModel setting) {
            this.setting = setting;
        }

        public VideoStreamDataModel getVideo_streaming() {
            return video_streaming;
        }

        public void setVideo_streaming(VideoStreamDataModel video_streaming) {
            this.video_streaming = video_streaming;
        }

        public PushTypes getPush_types() {
            return push_types;
        }

        public void setPush_types(PushTypes push_types) {
            this.push_types = push_types;
        }
    }

    /**
     * Walk through data
     */
    public class WalkthroughModel {
        public ArrayList<DataModel> data;

        public ArrayList<DataModel> getData() {
            return data;
        }

        public void setData(ArrayList<DataModel> data) {
            this.data = data;
        }

        public class DataModel {
            private String url, description_en, description_ar;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDescription_en() {
                return description_en;
            }

            public void setDescription_en(String description_en) {
                this.description_en = description_en;
            }

            public String getDescription_ar() {
                return description_ar;
            }

            public void setDescription_ar(String description_ar) {
                this.description_ar = description_ar;
            }
        }

    }


    /**
     * Setting data
     */
    public class SettingModel {
        public ArrayList<DataModel> data;

        public ArrayList<DataModel> getData() {
            return data;
        }

        public void setData(ArrayList<DataModel> data) {
            this.data = data;
        }

        public class DataModel {
            private String league_selection_limit, team_selection_limit;

            public String getLeague_selection_limit() {
                return league_selection_limit;
            }

            public void setLeague_selection_limit(String league_selection_limit) {
                this.league_selection_limit = league_selection_limit;
            }

            public String getTeam_selection_limit() {
                return team_selection_limit;
            }

            public void setTeam_selection_limit(String team_selection_limit) {
                this.team_selection_limit = team_selection_limit;
            }
        }

    }

    /**
     * Push message channel data
     */
    public class PushMessageChannelModel {
        public String pubnub_private_channel_prefix;
        private String pubnub_league_details_prefix;
        public String subscribe_key;
        public String secret_key;
        public String publish_key;

        public ArrayList<String> data;
        public List<String> league = new ArrayList<>();

        public String getPubnub_private_channel_prefix() {
            return pubnub_private_channel_prefix;
        }

        public String getPubnub_league_details_prefix() {
            return pubnub_league_details_prefix;
        }

        public void setPubnub_league_details_prefix(String pubnub_league_details_prefix) {
            this.pubnub_league_details_prefix = pubnub_league_details_prefix;
        }

        public void setPubnub_private_channel_prefix(String pubnub_private_channel_prefix) {
            this.pubnub_private_channel_prefix = pubnub_private_channel_prefix;
        }

        public ArrayList<String> getData() {
            return data;
        }

        public void setData(ArrayList<String> data) {
            this.data = data;
        }

        public String getSubscribe_key() {
            return subscribe_key;
        }

        public void setSubscribe_key(String subscribe_key) {
            this.subscribe_key = subscribe_key;
        }

        public String getSecret_key() {
            return secret_key;
        }

        public void setSecret_key(String secret_key) {
            this.secret_key = secret_key;
        }

        public String getPublish_key() {
            return publish_key;
        }

        public void setPublish_key(String publish_key) {
            this.publish_key = publish_key;
        }

        public void setLeagues(List<String> leages) {
            league = new ArrayList<>(leages);
        }

        public List<String> getLeague() {
            return league;
        }
    }

    /**
     * Video streaming data
     */
    public class VideoStreamDataModel {
        ArrayList<VideoDataModel> data = new ArrayList<>();

        public ArrayList<VideoDataModel> getData() {
            return data;
        }

        public void setData(ArrayList<VideoDataModel> data) {
            this.data = data;
        }
    }

    public class VideoDataModel {
        public String username;
        public String password;

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
    }

    /**
     * Push Type data
     **/
    public class PushTypes {
        public int discussion_post_like, comment_like, comment_add,
                new_discussion_post, user_follow, match_lineups,
                match_reminder, match_substitute, match_goal, match_cards,
                match_commentary, match_score, match_start, match_end,
                new_channel_post, config_file_updated, msg_chat;

        public int getDiscussion_post_like() {
            return discussion_post_like;
        }

        public void setDiscussion_post_like(int discussion_post_like) {
            this.discussion_post_like = discussion_post_like;
        }

        public int getComment_like() {
            return comment_like;
        }

        public void setComment_like(int comment_like) {
            this.comment_like = comment_like;
        }

        public int getComment_add() {
            return comment_add;
        }

        public void setComment_add(int comment_add) {
            this.comment_add = comment_add;
        }

        public int getNew_discussion_post() {
            return new_discussion_post;
        }

        public void setNew_discussion_post(int new_discussion_post) {
            this.new_discussion_post = new_discussion_post;
        }

        public int getUser_follow() {
            return user_follow;
        }

        public void setUser_follow(int user_follow) {
            this.user_follow = user_follow;
        }

        public int getMatch_lineups() {
            return match_lineups;
        }

        public void setMatch_lineups(int match_lineups) {
            this.match_lineups = match_lineups;
        }

        public int getMatch_reminder() {
            return match_reminder;
        }

        public void setMatch_reminder(int match_reminder) {
            this.match_reminder = match_reminder;
        }

        public int getMatch_substitute() {
            return match_substitute;
        }

        public void setMatch_substitute(int match_substitute) {
            this.match_substitute = match_substitute;
        }

        public int getMatch_goal() {
            return match_goal;
        }

        public void setMatch_goal(int match_goal) {
            this.match_goal = match_goal;
        }

        public int getMatch_cards() {
            return match_cards;
        }

        public void setMatch_cards(int match_cards) {
            this.match_cards = match_cards;
        }

        public int getMatch_commentary() {
            return match_commentary;
        }

        public void setMatch_commentary(int match_commentary) {
            this.match_commentary = match_commentary;
        }

        public int getMatch_score() {
            return match_score;
        }

        public void setMatch_score(int match_score) {
            this.match_score = match_score;
        }

        public int getMatch_start() {
            return match_start;
        }

        public void setMatch_start(int match_start) {
            this.match_start = match_start;
        }

        public int getMatch_end() {
            return match_end;
        }

        public void setMatch_end(int match_end) {
            this.match_end = match_end;
        }

        public int getNew_channel_post() {
            return new_channel_post;
        }

        public void setNew_channel_post(int new_channel_post) {
            this.new_channel_post = new_channel_post;
        }

        public int getConfig_file_updated() {
            return config_file_updated;
        }

        public void setConfig_file_updated(int config_file_updated) {
            this.config_file_updated = config_file_updated;
        }

        public int getMsg_chat() {
            return msg_chat;
        }

        public void setMsg_chat(int msg_chat) {
            this.msg_chat = msg_chat;
        }
    }


}
