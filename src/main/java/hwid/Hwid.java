package hwid;

import hwid.util.StringUtil;
import hwid.util.Webhook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *
 * @Author Vp (https://github.com/herravp)
 * @Author CCarbon1024 made forge version (https://github.com/CCarbon1024)
 * Code is free to use :)
 *
 */

// Made using Fleet :gigachad: :muscle:
public class Hwid {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        LOGGER.info("正在验证白名单...");
        if (!hwid.Hwid.validateHwid()) {
            LOGGER.error("MOD白名单申请 https://docs.qq.com/form/page/DVHBzYm5BZG1NZVVR");
            LOGGER.error("匹配失败！请复制以下数字并填写申请表");
            LOGGER.error(getHwid());
            System.exit(1);
        } else {
            LOGGER.info("通过验证！成功登录！");
            try {
                hwid.Hwid.sendWebhook();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Huge auth thing fr
    public static boolean validateHwid() {
        String hwid = getHwid();
        System.out.println(hwid);

        try {
            // replace the example with ur own url
            URL url = new URL("https://userpass.onmicrosoft.cn/login?hwid=" + hwid);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(hwid)) return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Send a discord webhook when someone is logging in
    public static void sendWebhook() throws IOException {
        try {
            // ur webhook url, if u even want to use webhook.
            Webhook webhook = new Webhook("");
            Webhook.EmbedObject embed = new Webhook.EmbedObject();

            embed.setTitle("hwid");
            embed.setThumbnail("null");
            embed.setDescription("New login " + getHwid());
            embed.setColor(Color.GRAY);
            embed.setFooter(getTime(), null);
            webhook.addEmbed(embed);

            if (validateHwid()) webhook.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getHwid() {
        StringBuilder returnhwid = new StringBuilder();
        // You can make it even more secure, but I'll use this for example now.
        String hwid = System.getProperty("user.name") + System.getProperty("user.home") + System.getProperty("os.version") + System.getProperty("os.name");
        for (String s : StringUtil.getSubstrings(hwid)) {
            returnhwid.append(StringUtil.convertToString(s));
        }
        return returnhwid.toString();
    }

    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        return (formatter.format(date));
    }
}