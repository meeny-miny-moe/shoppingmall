package shoppingmall.shopping_mall.itemService.question;

import shoppingmall.shopping_mall.itemService.item.Item;

import java.util.List;

public interface QuestionRepository {
    Question save(Question question);

    Question findById(Long id);

    List<Question> findAll();

    void update(Long questionId, Question updateParam);

    void clearStore();
}
