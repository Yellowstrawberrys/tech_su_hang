package xyz.yellowstrawberry.haksial;

import me.yellowstrawberry.openneisapi.ONA;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class Main {
    public static JDA jda;
    public static ONA ona = new ONA.Builder("4a44a45e0d5341deba87a972a95fa5ba").build();
    private static String token = "MTExNzczNzQyMzU0MDA2MDE4MA.GHtyB4.cbZbx3-0tHqLYayTvmLvUr4c1ZnPMR0kUHjHF0";

    public static void main(String[] args) {
        System.out.println("Starting Hak-Si-Al Bot...");

        //Create JDA instance
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        jdaBuilder.setActivity(Activity.playing("학교 시간표랑 급식표 가져오는중!"));
        jdaBuilder.addEventListeners(new MessageHandler());
        jda = jdaBuilder.build();

        //Register Command (커맨드 등록)
        jda.upsertCommand("급식표", "급식표를 알려줍니다.").addOption(OptionType.STRING, "school", "학교 이름", true).queue();
        jda.upsertCommand("시간표", "시간표를 알려줍니다.")
                .addOption(OptionType.STRING, "school", "학교 이름", true)
                .addOption(OptionType.INTEGER, "grade", "학년", true)
                .addOption(OptionType.STRING, "classname", "반 이름", true)
                .queue();


    }
}
