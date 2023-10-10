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
            return "❤\uFE0F";
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
    },

    HAND {
        @Override
        public String toString() {return "\uD83D\uDED2";}
    },

    MONEY{
        @Override
        public String toString() { return "\uD83D\uDCB2"; }
    },

    REPUTATION {
        @Override
        public String toString() { return "\uD83D\uDC51"; }

    },

    GUN {
        @Override
        public String toString() { return "\uD83D\uDD2B";}
    },

    HARM {
        @Override
        public String toString() { return "\uD83D\uDCA5";}
    },

    ACCURACY {
        @Override
        public String toString() { return "\uD83C\uDFAF";}
    },

    STASH {
        @Override
        public String toString() { return "\uD83D\uDCBC";}
    }
    ;


}
