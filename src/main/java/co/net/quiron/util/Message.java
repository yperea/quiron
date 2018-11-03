package co.net.quiron.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Message {

    @NonNull
    protected MessageType type;
    @NonNull
    protected String description;
    protected String redirect;
}
