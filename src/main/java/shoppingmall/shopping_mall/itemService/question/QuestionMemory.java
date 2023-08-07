package shoppingmall.shopping_mall.itemService.question;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class QuestionMemory implements QuestionRepository{
    private static final Map<Long, Question> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    @Override
    public Question save(Question question) {
        question.setId(++sequence);
        store.put(question.getId(), question);
        return question;
    }

    @Override
    public Question findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long questionId, Question updateParam) {
        Question findQuestion = findById(questionId);
        findQuestion.setQuestionType(updateParam.getQuestionType());
        findQuestion.setWriter(updateParam.getWriter());
        findQuestion.setEmail(updateParam.getEmail());
        findQuestion.setImage(updateParam.getImage());
        findQuestion.setPassword(updateParam.getPassword());
        findQuestion.setExplanation(updateParam.getExplanation());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
