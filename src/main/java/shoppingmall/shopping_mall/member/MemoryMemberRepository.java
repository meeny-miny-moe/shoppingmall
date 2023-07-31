package shoppingmall.shopping_mall.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
@Component
public class MemoryMemberRepository implements MemberRepository {
    /**
     * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: memberId={}", member.getLoginId());
        log.info("save: memberPW={}", member.getPassword());
        log.info("save: memberName={}", member.getName());
        log.info("save: memberNumber={}", member.getNumber());
        log.info("save: memberEmail={}", member.getEmail());
        log.info("save: id={}", member.getId());
        store.put(member.getId(), member);
        return member;
    }
    public Member findById(Long id) {
        return store.get(id);
    }
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}
