package co.net.quiron.util;

import lombok.Data;


@Data
public class Message {

    protected MessageType type;
    protected String description;
    protected String redirect;
}
