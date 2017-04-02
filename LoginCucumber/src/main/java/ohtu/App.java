package ohtu;

import ohtu.data_access.InMemoryUserDao;
import ohtu.data_access.UserDao;
import ohtu.io.ConsoleIO;
import ohtu.io.IO;
import ohtu.services.AuthenticationService;

public class App {

    private IO io;
    private AuthenticationService auth;

    public App(IO io, AuthenticationService auth) {
        this.io = io;
        this.auth = auth;
    }

    public String[] ask() {
        String[] userPwd = new String[2];
        userPwd[0] = io.readLine("username:");
        userPwd[1] = io.readLine("password:");
        return userPwd;
    }

    private boolean newNameAndPwdValid(String[] str) {
        String name = str[0];
        String pwd = str[1];

        if (containsWeirdMarks(name) || properLength(name, 3) || properLength(pwd, 8) ||
                containsSpecialMark(pwd)) {
            return false;
        }

        return true;
    }
    
//    private boolean nameNotInUse(String name){
//        if(auth.)
//    }
    
    private boolean properLength(String str, int len) {
        if (str.length() >= len) {
            return true;
        }
        return false;
    }

//    private boolean containsSpecialMark(String str) {
//        //tarkistus että str joukossa erikoismerkki
//        String accepted = "qazwsxedcrfvtgbyhnujmikolp";
//        int length = str.length();
//
//        for (int i = 0; i < length; i++) {
//            if (!accepted.contains("" + str.charAt(i))) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    private boolean containsWeirdMarks(String str) {
        //tarkistus että str joukossa P(a-z)
        String accepted = "qazwsxedcrfvtgbyhnujmikolp";
        int length = str.length();

        for (int i = 0; i < length; i++) {
            if (!accepted.contains("" + str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public void run() {
        while (true) {
            String command = io.readLine("komento (new tai login):");
            if (command.isEmpty()) {
                break;
            }

            if (command.equals("new")) {
                String[] usernameAndPasword = ask();
                if (newNameAndPwdValid(usernameAndPasword) && auth.createUser(usernameAndPasword[0], usernameAndPasword[1])) {

                    io.print("new user registered");
                } else {
                    io.print("new user not registered");
                }

            } else if (command.equals("login")) {
                String[] usernameAndPasword = ask();
                if (auth.logIn(usernameAndPasword[0], usernameAndPasword[1])) {
                    io.print("logged in");
                } else {
                    io.print("wrong username or password");
                }
            }

        }
    }

    public static void main(String[] args) {
        UserDao dao = new InMemoryUserDao();
        IO io = new ConsoleIO();
        AuthenticationService auth = new AuthenticationService(dao);
        new App(io, auth).run();
    }

    // testejä debugatessa saattaa olla hyödyllistä testata ohjelman ajamista
    // samoin kuin testi tekee, eli injektoimalla käyttäjän syötteen StubIO:n avulla
    //
    // UserDao dao = new InMemoryUserDao();  
    // StubIO io = new StubIO("new", "eero", "sala1nen" );   
    //  AuthenticationService auth = new AuthenticationService(dao);
    // new App(io, auth).run();
    // System.out.println(io.getPrints());
}
