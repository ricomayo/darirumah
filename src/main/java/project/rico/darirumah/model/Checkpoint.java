package project.rico.darirumah.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;



@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Checkpoint<T> implements Serializable {
    private String key;
    private int timeToLive;
    private T value;
    private Date createdAt;

    public Checkpoint(String key, int timeToLive, T data) {
        this.key = key;
        this.timeToLive = timeToLive;
        this.value = data;
    }

    public void now() {
        this.createdAt = new Date();
    }
}
