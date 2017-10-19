package ru.hostco.burovalex.webapptest;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class Main {

        private int count;

        @Init
        public void init() {
            count = 100;
        }

        @Command
        @NotifyChange("count")
        public void cmd() {
            ++count;
        }

        public int getCount() {
            return count;
        }
}
