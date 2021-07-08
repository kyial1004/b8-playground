package pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SlackPojo {

    private boolean ok;
    private  String channel;
    private  SlackMessagePojo message;


}
