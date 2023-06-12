package xyz.yellowstrawberry.haksial;

import me.yellowstrawberry.openneisapi.objects.food.Food;
import me.yellowstrawberry.openneisapi.objects.food.SchoolMeal;
import me.yellowstrawberry.openneisapi.objects.schedule.Period;
import me.yellowstrawberry.openneisapi.objects.school.School;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

public class MessageHandler extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String schoolName = event.getOption("school").getAsString();
        try {
            School school = Main.ona.searchSchool(schoolName)[0];
            if(event.getFullCommandName().equals("시간표")) {
                StringBuilder sb = new StringBuilder();

                sb.append("오늘의 시간표\n\n");


                Period[] periods = Main.ona.getScheduleOfDay(school, event.getOption("grade").getAsInt(), event.getOption("classname").getAsString(), Date.from(Instant.now()));
                for(Period period : periods) {
                    sb.append(period.getPeriod()).append(" | ").append(period.getName()).append("\n");
                }

                event.reply(sb.toString()).queue();
            }else if(event.getFullCommandName().equals("급식표")) {
                StringBuilder sb = new StringBuilder();

                sb.append("오늘의 급식표\n\n");

                SchoolMeal meal = Main.ona.getMealOfDay(school, Date.from(Instant.now()));

                for(Food food : meal.getFood()) {
                    sb.append(food.getName()).append(", ");
                }
                sb.delete(sb.length()-3, sb.length()-1);
                sb.append("\n\n");

                sb.append("공급 인원: ").append(meal.getCaterers()).append("\n");
                sb.append("칼로리: ").append(meal.getKcal());

                event.reply(sb.toString()).queue();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
