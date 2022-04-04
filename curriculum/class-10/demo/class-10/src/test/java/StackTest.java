import com.demo.dsa.stack.data.StackNode;
import com.demo.dsa.stack.structure.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    Stack stack;

    @BeforeEach
    void setUp() {
        stack = new Stack();
    }

    @Test
    @DisplayName("testStackPush")
    void testStackPush() {
        StackNode expected1 = new StackNode("James Bond", "007-007-007");
        StackNode expected2 = new StackNode("Sally Bond", "007-007-007");
        StackNode expected3 = new StackNode("Morgan Bond", "007-007-007");
        StackNode expected4 = new StackNode("Jules Bond", "007-007-007");
        StackNode actual1 = stack.push(expected1);
        StackNode actual2 = stack.push(expected2);
        StackNode actual3 = stack.push(expected3);
        StackNode actual4 = stack.push(expected4);

        assertEquals(expected4, actual4);
    }
}
