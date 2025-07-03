package NewsApp.exception;

import java.util.List;

public class CategoriesNotFoundException extends BusinessException {
    public CategoriesNotFoundException(List<Long> notFoundIds) {
        super(
                ErrorCode.CATEGORIES_NOT_FOUND,
                "Categories not found: " + notFoundIds
        );
    }
}