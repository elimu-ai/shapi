package fr.tvbarthel.apps.shapi.shape.generation;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import fr.tvbarthel.apps.shapi.game.GameLevels;
import fr.tvbarthel.apps.shapi.shape.Circle;
import fr.tvbarthel.apps.shapi.shape.Diamond;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Tests for the class {@link RandomShapeGenerator}
 */
@RunWith(AndroidJUnit4.class)
public class RandomShapeGeneratorTest {

    @Mock
    private Random random;
    private RandomShapeGenerator randomShapeGenerator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        randomShapeGenerator = new RandomShapeGenerator(random, 10);
    }

    @Test
    public void generateRectangle() {
        given(random.nextInt(ArgumentMatchers.anyInt())).willReturn(0);

        final Shape shape = randomShapeGenerator.generate(GameLevels.LEVEL_1);

        assertThat(shape, instanceOf(Rectangle.class));
    }

    @Test
    public void generateTriangle() {
        given(random.nextInt(ArgumentMatchers.anyInt())).willReturn(1);

        final Shape shape = randomShapeGenerator.generate(GameLevels.LEVEL_1);

        assertThat(shape, instanceOf(Triangle.class));
    }

    @Test
    public void generateCircle() {
        given(random.nextInt(ArgumentMatchers.anyInt())).willReturn(2);

        final Shape shape = randomShapeGenerator.generate(GameLevels.LEVEL_1);

        assertThat(shape, instanceOf(Circle.class));
    }

    @Test
    public void generateDiamond() {
        given(random.nextInt(ArgumentMatchers.anyInt())).willReturn(3);

        final Shape shape = randomShapeGenerator.generate(GameLevels.LEVEL_1);

        assertThat(shape, instanceOf(Diamond.class));
    }
}
