package com.sorianotapia.headings;

public enum HeadingNames {
    STAT_PAGE_DRUGS {
        public String getTemplate(){
            return """
            ╔═════════════════════════════════════════════════════════════════════════════╗
            ║                       * * * D R U G  L O R D * * *                          ║
            ╠═══ Date: %17$s ═══════════════════════════╦══════════════════════════════╣
            ║                                              ║   Location: %18$-16s ║
            ╠═══ Drugs on Hand ═══╦════ Street Prices ═════╣       Hold: %19$-2d     (%20$2d max)  ║
            ║                     ║                        ║                              ║
            ║   Cocaine       %1$3d ║   Cocaine        %9$5d ║       Cash:  $%21$-8d       ║
            ║   Crack         %2$3d ║   Crack          %10$5d ║    In Bank:  $%22$-8d       ║
            ║   Heroin        %3$3d ║   Heroin         %11$5d ║    In Debt:  $%23$-8d  %24$4s ║
            ║   Acid          %4$3d ║   Acid           %12$5d ║                              ║
            ║   Crystal       %5$3d ║   Crystal        %13$5d ║  # of Guns: %25$-3d              ║
            ║   Grass         %6$3d ║   Grass          %14$5d ║   Gun Type: %26$-16s ║
            ║   Speed         %7$3d ║   Speed          %15$5d ║                              ║
            ║   Ludes         %8$3d ║   Ludes          %16$5d ║  Status Pts: %27$-3d             ║
            ║                     ║                        ║      Health: %28$-3d             ║
            ╚═════════════════════╩════════════════════════╩══════════════════════════════╝
                                                                            (Q)uit the game
                        
            """;
        }
    };

    public abstract String getTemplate();
}
