package bai.demo.ssedemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author aCuteKiller
 * @blog http://www.baifun.top
 * @date 2025-04-27 15:23
 * @description
 */

@NoArgsConstructor
public class MessageVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String sender;
    private String message;

    public MessageVO(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
