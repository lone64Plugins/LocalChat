package org.lone64.localchat.util;

import org.bukkit.ChatColor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static String replaceLegacyColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static Iterable<MatchResult> allMatches(final Pattern p, final CharSequence input) {
        return () -> new Iterator<>() {
            final Matcher matcher = p.matcher(input);
            MatchResult pending;

            public boolean hasNext() {
                if (pending == null && matcher.find()) {
                    pending = matcher.toMatchResult();
                }
                return pending != null;
            }

            public MatchResult next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MatchResult next = pending;
                pending = null;
                return next;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
