import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        for (int i=0; i<8; i++){
            ad.addLast(i);
        }
        assertThat(ad.size()).isEqualTo(8);
        assertThat(ad.toList()).containsExactly(0,1,2,3,4,5,6,7).inOrder();

        ad.addLast(2);
        ad.addFirst(5);
        assertThat(ad.size()).isEqualTo(10);

        assertThat(ad.toList()).containsExactly(5,0,1,2,3,4,5,6,7,2).inOrder();
    }
}
