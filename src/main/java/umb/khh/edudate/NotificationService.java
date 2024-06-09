package umb.khh.edudate;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyUser(Long userId, String message) {
        // Логика отправки уведомлений пользователям
        // Например, отправка уведомлений по электронной почте или push-уведомлений
        System.out.println("Уведомление для пользователя " + userId + ": " + message);
    }
}
