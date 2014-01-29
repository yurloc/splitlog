package com.github.triceo.splitlog;

public class Message {

    private final RawMessage rawMessage;
    private final MessageSeverity severity;
    private final MessageType type;

    public Message(final RawMessage raw, final MessageClassifier<MessageType> typeClassifier,
            final MessageClassifier<MessageSeverity> severityClassifier) {
        this.rawMessage = raw;
        this.severity = severityClassifier.classify(raw);
        this.type = typeClassifier.classify(raw);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (this.rawMessage == null) {
            if (other.rawMessage != null) {
                return false;
            }
        } else if (!this.rawMessage.equals(other.rawMessage)) {
            return false;
        }
        return true;
    }

    public RawMessage getRawMessage() {
        return this.rawMessage;
    }

    public MessageSeverity getSeverity() {
        return this.severity;
    }

    public MessageType getType() {
        return this.type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.rawMessage == null) ? 0 : this.rawMessage.hashCode());
        return result;
    }

}
