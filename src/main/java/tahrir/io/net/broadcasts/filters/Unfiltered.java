package tahrir.io.net.broadcasts.filters;

import com.google.common.base.Predicate;
import com.sun.istack.internal.Nullable;
import tahrir.io.net.broadcasts.broadcastMessages.ParsedBroadcastMessage;

/**
 * User: ravisvi <ravitejasvi@gmail.com>
 * Date: 23/07/13
 */
public class Unfiltered implements Predicate<ParsedBroadcastMessage> {
    @Override
    public boolean apply(@Nullable ParsedBroadcastMessage parsedBroadcastMessage) {
        return true;
    }
}