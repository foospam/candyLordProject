//package com.sorianotapia.fromVersion1;
//
//
//public class MainScreenHeader extends Screen {
//
//
//
//
//    String master = """
//            ╔═════════════════════════════════════════════════════════════════════════════╗
//            ║                       * * * D R U G  L O R D * * *                          ║
//            ╠═══ Date: 09/28/90 ═══════════════════════════╦══════════════════════════════╣
//            ║                                              ║   Location: Los Angeles      ║
//            ╠═══ Drugs on Hand ═══╦════ Street Prices ═════╣       Hold: 10     (10 max)  ║
//            ║                     ║                        ║                              ║
//            ║   Cocaine         0 ║   Cocaine        50444 ║       Cash:  $136            ║
//            ║   Crack           0 ║   Crack                ║    In Bank:  $0              ║
//            ║   Heroin          0 ║   Heroin               ║    In Debt:  $0              ║
//            ║   Acid            0 ║   Acid                 ║                              ║
//            ║   Crystal         0 ║   Crystal              ║  # of Guns: 0                ║
//            ║   Grass           0 ║   Grass                ║   Gun Type: None             ║
//            ║   Speed           0 ║   Speed                ║                              ║
//            ║   Ludes           0 ║   Ludes                ║  Status Pts: 1               ║
//            ║                     ║                        ║      Health: 100             ║
//            ╚═════════════════════╩════════════════════════╩══════════════════════════════╝
//                                                                            (Q)uit the game
//
//            """;
//
//
//    static final String MASTER_PATTERN = """
//            ╔═════════════════════════════════════════════════════════════════════════════╗
//            ║                       * * * D R U G  L O R D * * *                          ║
//            ╠═══ Date: %17$s ═══════════════════════════╦══════════════════════════════╣
//            ║                                              ║   Location: %18$-16s ║
//            ╠═══ Drugs on Hand ═══╦════ Street Prices ═════╣       Hold: %19$-2d     (%20$2d max)  ║
//            ║                     ║                        ║                              ║
//            ║   Cocaine       %1$3d ║   Cocaine        %9$5d ║       Cash:  $%21$-8d       ║
//            ║   Crack         %2$3d ║   Crack          %10$5d ║    In Bank:  $%22$-8d       ║
//            ║   Heroin        %3$3d ║   Heroin         %11$5d ║    In Debt:  $%23$-8d  %24$4s ║
//            ║   Acid          %4$3d ║   Acid           %12$5d ║                              ║
//            ║   Crystal       %5$3d ║   Crystal        %13$5d ║  # of Guns: %25$-3d              ║
//            ║   Grass         %6$3d ║   Grass          %14$5d ║   Gun Type: %26$-16s ║
//            ║   Speed         %7$3d ║   Speed          %15$5d ║                              ║
//            ║   Ludes         %8$3d ║   Ludes          %16$5d ║  Status Pts: %27$-3d             ║
//            ║                     ║                        ║      Health: %28$-3d             ║
//            ╚═════════════════════╩════════════════════════╩══════════════════════════════╝
//                                                                            (Q)uit the game
//
//            """;
//
//    private static Object[] currentData;
//
////    public MainScreenHeader(Screen header, String prompt, String successMessage, String failureMessage, Controller controller, UserInputHandler inputHandler) {
////        super(header, prompt, successMessage, failureMessage, controller, inputHandler);
////    }
//
////    public void updateCurrentData(Player player, City city){
////        Formatter formatter = new Formatter();
////    }
//
//    public void render(){
//        Object[] data = getAllData();
//        System.out.println(String.format(MASTER_PATTERN, data));
//    }
//
//    public Object[] getAllData(){
//        Object[] objects = new Object[28];
//
//        objects[0] = player.getDrugsOnHand(Drugs.COCAINE);
//        objects[1] = player.getDrugsOnHand(Drugs.CRACK);
//        objects[2] = player.getDrugsOnHand(Drugs.HEROIN);
//        objects[3] = player.getDrugsOnHand(Drugs.ACID);
//        objects[4] = player.getDrugsOnHand(Drugs.CRYSTAL);
//        objects[5] = player.getDrugsOnHand(Drugs.GRASS);
//        objects[6] = player.getDrugsOnHand(Drugs.SPEED);
//        objects[7] = player.getDrugsOnHand(Drugs.LUDES);
//        objects[8] = Drugs.COCAINE.getPrice();
//        objects[9] = Drugs.CRACK.getPrice();
//        objects[10] = Drugs.HEROIN.getPrice();
//        objects[11] = Drugs.ACID.getPrice();
//        objects[12] = Drugs.CRYSTAL.getPrice();
//        objects[13] = Drugs.GRASS.getPrice();
//        objects[14] = Drugs.SPEED.getPrice();
//        objects[15] = Drugs.LUDES.getPrice();
//        objects[16] = GameDate.getStringDate();
//        objects[17] = player.getLocation().printableName();
//        objects[18] = player.getHold();
//        objects[19] = player.getMaxHold();
//        objects[20] = player.getCash();
//        objects[21] = player.getDeposits();
//        objects[22] = player.getDebtValue();
//        objects[23] = player.getDeposits() > 0 ?  String.format("(%2d)", player.getDebtDays()) : "";
//        objects[24] = player.getNumberOfGuns();
//        objects[25] = player.getGunType();
//        objects[26] = player.getReputation();
//        objects[27] = player.getHealth();
//
//        return objects;
//    }
//
//
//
//}
