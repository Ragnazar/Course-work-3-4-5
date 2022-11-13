package pro.sky.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
@EnableScheduling
public class TelegramBotApplication {
	private final NotificationTaskRepository repository;
	private final TelegramBot telegramBot;

	public TelegramBotApplication(NotificationTaskRepository repository, TelegramBot telegramBot) {
		this.repository = repository;
		this.telegramBot = telegramBot;
	}

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);
	}
	@Scheduled(cron = "0 0/1 * * * *")
	public void run() {

		NotificationTask task =  repository.findByTimeSend(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
		if (task!=null) {
			SendMessage reminder = new SendMessage(task.getChatId(), task.getMessage());
			telegramBot.execute(reminder);
		}
	}
}
