package awsreactspring.jong.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import awsreactspring.jong.domain.SiteUser;
import awsreactspring.jong.repository.UserRepository;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {

    @Autowired UserService userService;
    @Autowired MatchingService matchingService;
    @Autowired UserRepository userRepository;

    @Test
    @Commit
    void 회원가입() throws Exception {
        //given
        SiteUser user = new SiteUser();
        user.setEmail("hy@naver.com");
        user.setPassword("1234");
        user.setName("현");
        user.setPhone("010-1234-1240");
        user.setBirth("2000.04.29");
        user.setAddress("충북 충주시 충원대로 539");               

        //when
        String Check = userService.join(user);
        
        //then
        System.out.println(Check);
    }

    @Test
    void 로그인() throws Exception {
        //given
        SiteUser user = new SiteUser();
        user.setEmail("jong@naver.com");
        user.setPassword("1234");
        user.setName("jong");
        user.setPhone("010-1234-1234");
        user.setBirth("2000.04.29");
        user.setAddress("충주시");  

        SiteUser coruser = new SiteUser();
        coruser.setEmail("jong@naver.com");
        coruser.setPassword("1234");

        SiteUser emailuser = new SiteUser();
        emailuser.setEmail("j@naver.com");
        emailuser.setPassword("1234");

        SiteUser passuser = new SiteUser();
        passuser.setEmail("jong@naver.com");
        passuser.setPassword("12345");


        //when
        String Check = userService.join(user);

        //then
        userService.login(coruser);

        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> userService.login(emailuser));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이메일이 없습니다.");

        IllegalStateException er = assertThrows(IllegalStateException.class,
            () -> userService.login(passuser));//예외가 발생해야 한다.
        assertThat(er.getMessage()).isEqualTo("비밀번호가 일치 하지 않음.");


    }
    @Test
    void 회원정보제공() throws Exception{
        //given

        SiteUser user = new SiteUser();
        user.setEmail("jong@naver.com");
        user.setPassword("1234");
        user.setName("jong");
        user.setPhone("010-1234-1234");
        user.setBirth("2000.04.29");
        user.setAddress("충주시"); 

        //when
        String Check = userService.join(user);

        SiteUser getUser = userService.finduser(user);
        System.out.println(getUser.getAddress());        
        System.out.println(getUser.getEmail());


    }
    @Test
    @Commit
    void 정보수정() throws Exception{
        SiteUser user = new SiteUser();
        user.setEmail("jong@naver.com");
        user.setPassword("12345");
        user.setName("jong");
        user.setPhone("010-1234-1234");
        user.setBirth("2000.03.29");
        user.setAddress("대한민국 청주시"); 

        userService.changeUser(user);
    }
    @Test
    void 함수테스트() throws Exception{

        //테스트 데이터 기입
        SiteUser user = new SiteUser();
        user.setEmail("pjh@naver.com");
        user.setPassword("1234");
        user.setName("박종현");
        user.setPhone("010-1234-1234");
        user.setBirth("2000.04.29");
        user.setAddress("충북 충주시 충원대로 539");
        user.setScore(0);
        user.setSex("남");
        user.setWorker(false);
        
        
        SiteUser user1 = new SiteUser();
        user1.setEmail("pj@naver.com");
        user1.setPassword("1234");
        user1.setName("박종");
        user1.setPhone("010-1234-1235");
        user1.setBirth("2000.04.29");
        user1.setAddress("충북 충주시 봉계1길 49");
        user1.setScore(0);
        user1.setSex("남");
        user1.setWorker(true);
        
        SiteUser user2 = new SiteUser();
        user2.setEmail("j@naver.com");
        user2.setPassword("1234");
        user2.setName("종");
        user2.setPhone("010-1234-1236");
        user2.setBirth("2000.04.29");
        user2.setAddress("충북 충주시 대소원면 대학로 50");
        user2.setScore(0);
        user2.setSex("여");
        user2.setWorker(true);

        
        SiteUser user3 = new SiteUser();
        user3.setEmail("ph@naver.com");
        user3.setPassword("1234");
        user3.setName("박현");
        user3.setPhone("010-1234-1237");
        user3.setBirth("2000.04.29");
        user3.setAddress("충북 충주시 대소원면 대학로 28");
        user3.setScore(0);
        user3.setSex("여");
        user3.setWorker(true);

        SiteUser user4 = new SiteUser();
        user4.setEmail("h@naver.com");
        user4.setPassword("1234");
        user4.setName("현");
        user4.setPhone("010-1234-1238");
        user4.setBirth("2000.04.29");
        user4.setAddress("충북 충주시 대소원면 대학로 30");
        user4.setScore(0);
        user4.setSex("남");
        user4.setWorker(true);

        SiteUser testuser = new SiteUser();
        testuser.setEmail("h@naver.com");
        testuser.setPassword("1234");
        testuser.setName("현");
        testuser.setPhone("010-1234-1239");
        testuser.setBirth("2000.04.29");
        testuser.setAddress("충북 충주시 대소원면 대학로 32");
        testuser.setScore(0);
        testuser.setSex("남");
        testuser.setWorker(false);

        //테스트 데이터 DB 입력
        String Check = userService.join(user);
        String Check1 = userService.join(user1);
        String Check2 = userService.join(user2);
        String Check3 = userService.join(user3);
        String Check4 = userService.join(user4);      
        String Check5 = userService.join(testuser);
        
        //간병인만 모은 DB가져옴
        List<SiteUser> users = userService.findWorkers(true);

        //매칭 점수 매기기
        matchingService.matchingScore(users, testuser);

        //점수별로 가져옴
        List<SiteUser> Score = matchingService.highScore();

        for (SiteUser siteuser : Score) {
            System.out.println(siteuser.getName()+ " " + siteuser.getScore());
        }

    }
}
