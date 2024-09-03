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

    @Test
    public void GetTest(){
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        assertThat(ad.get(0)).isNull();
        ad.addFirst(1);
        ad.addFirst(5);
        assertThat(ad.get(0)).isEqualTo(5);
        assertThat(ad.get(500)).isNull();
        assertThat(ad.get(-1)).isNull();
    }

    @Test
    public void removeFirstAndRemoveLastTest(){
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        assertThat(ad.removeLast()).isNull();
        assertThat(ad.removeFirst()).isNull();

        ad.addFirst(1);
        ad.addLast(2);
        ad.addLast(3);
        ad.addFirst(4);// List is [4, 1, 2, 3]

        ad.removeFirst(); //List becomes [1, 2, 3]
        assertThat(ad.removeFirst()).isEqualTo(1); //List becomes [2, 3]
        assertThat(ad.toList()).containsExactly(2, 3).inOrder();

        ad.removeLast(); //List becomes [2]
        assertThat(ad.removeLast()).isEqualTo(2); //List becomes []
        assertThat(ad.toList()).isEmpty();
    }
    @Test
    public void resizeTest(){
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        assertThat(((ArrayDeque61B<Integer>) ad).internalCapacity()).isEqualTo(8);

        for (int i = 0; i < 16; i++) {
            ad.addLast(i);
        }
        assertThat(((ArrayDeque61B<Integer>) ad).internalCapacity()).isEqualTo(16);

        for (int i = 0; i < 13; i++) {
            ad.removeFirst();
        }
        assertThat(ad.size()).isEqualTo(3);

        if (ad.size() >=16) {
            assertThat(((ArrayDeque61B<Integer>) ad).internalCapacity()).isEqualTo(8);
        }
    }


}
