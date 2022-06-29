package api;

import java.io.Serializable;

public record SocketResponse(String header, Object body) implements Serializable {
}
