package com.sorianotapia.accessories;

public enum DisplaySymbols {
    POLICE_OFFICER {
        @Override
        public String toString() {
            return "\uD83D\uDC6E";
        }
    },

    GANGSTER {
        public String toString(){
            return "\uD83E\uDD78";
        }
    },

    PLAYER {
        @Override
        public String toString() {
            return "\uD83E\uDD35";
        }
    },

    STUFF_SYMBOL {
        @Override
        public String toString() {
            return "\uD83C\uDF6C";
        }
    },

    MAP {
        @Override
        public String toString() {
            return "\uD83C\uDF0D";
        }
    },

    TIME {
        @Override
        public String toString() {
            return "\uD83D\uDCC6";
        }
    },

    HEALTH {
        @Override
        public String toString() {
            return "\uD83D\uDCB0";
        }
    },

    CASH {
        @Override
        public String toString() {
            return "\uD83D\uDCB0";
        }
    },

    BANK {
        @Override
        public String toString() {
            return "\uD83C\uDFE6";
        }
    },

    CREDIT {
        @Override
        public String toString() {
            return "\uD83D\uDCB3";
        }
    }
    ;


}
