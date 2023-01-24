package drivewayparking.app.repository;

import drivewayparking.app.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select receiver_id as id from messages where sender_id = :uid \n" +
            "union \n" +
            "select sender_id as id from messages where receiver_id = :uid \n" +
            "order by id", nativeQuery = true)
    List<Long> findUserHistory(@Param("uid") Long id);

    @Query(value = "select * from messages where sender_id = :id and receiver_id = :otherId \n" +
            "union \n" +
            "select * from messages where sender_id = :otherId and receiver_id = :id \n" +
            "order by sent", nativeQuery = true)
    List<Message> getChatHistory(@Param("id") Long id, @Param("otherId") Long otherId);

//    @Query("SELECT m FROM Message m WHERE m.sender.id = :senderId AND m.receiver.id = :receiverId ORDER BY m.created DESC")
//    List<Message> findBySenderReceiver(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
