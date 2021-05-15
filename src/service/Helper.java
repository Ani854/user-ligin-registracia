package service;

import model.User;

public class Helper {
    public static User[] makeUsers(String[] strings) {
        User[] users = new User[strings.length];
        for (int i = 0; i < strings.length; i++) {
            users[i] = new User();
            String[] split = strings[i].split(",");
            users[i].setFullName(split[0]);
            users[i].setUsername(split[1]);
            users[i].setEmail(split[2]);
            users[i].setPassword(split[3]);
        }
        return users;
    }
    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
