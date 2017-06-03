package fr.tvbarthel.apps.shapi.engine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import fr.tvbarthel.apps.shapi.audioeffect.AudioEffectEngine;
import fr.tvbarthel.apps.shapi.audioeffect.AudioEffects;
import fr.tvbarthel.apps.shapi.shape.Rectangle;
import fr.tvbarthel.apps.shapi.shape.Shape;
import fr.tvbarthel.apps.shapi.shape.Triangle;
import fr.tvbarthel.apps.shapi.shape.generation.ShapeGenerator;
import fr.tvbarthel.apps.shapi.shape.identification.ShapeIdentifier;

import static junit.framework.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Test for the {@link GameEngine} class.
 */
public class GameEngineTest {

    @Mock
    ShapeIdentifier shapeIdentifier;
    @Mock
    ShapeGenerator shapeGenerator;
    @Mock
    AudioEffectEngine audioEffectEngine;
    @Mock
    List<Shape> correctlyIdentifiedShapes;
    @Mock
    List<Shape> incorrectlyIdentifiedShapes;
    @Mock
    Rectangle rectangleShape;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void currentScore() {
        // Given
        given(correctlyIdentifiedShapes.size()).willReturn(3);
        given(incorrectlyIdentifiedShapes.size()).willReturn(2);
        given(shapeIdentifier.getCorrectlyIdentifiedShapes()).willReturn(correctlyIdentifiedShapes);
        given(shapeIdentifier.getIncorrectlyIdentifiedShapes()).willReturn(incorrectlyIdentifiedShapes);

        // When
        final GameScore currentScore = createGameEngine().getCurrentScore();

        // Then
        assertEquals(3, currentScore.getNumberOfCorrectAnswers());
        assertEquals(2, currentScore.getNumberOfIncorrectAnswers());
    }

    @Test
    public void currentShape() {
        // Given
        given(shapeGenerator.generate()).willReturn(rectangleShape);

        // When
        final Shape currentShapeToIdentify = createGameEngine().getCurrentShapeToIdentify();

        // Then
        assertEquals(rectangleShape, currentShapeToIdentify);
    }

    @Test
    public void whenIdentifyShapeCorrectlyThenPlaySuccess() {
        // Given
        given(shapeGenerator.generate()).willReturn(rectangleShape);
        given(shapeIdentifier.identify(rectangleShape, Rectangle.class)).willReturn(true);

        // When
        createGameEngine().identifyCurrentShape(Rectangle.class);

        // Then
        verify(audioEffectEngine).play(AudioEffects.AUDIO_EFFECT_ID_SUCCESS);
    }

    @Test
    public void whenIdentifyShapeIncorrectlyThenPlayFailure() {
        // Given
        given(shapeGenerator.generate()).willReturn(rectangleShape);
        given(shapeIdentifier.identify(rectangleShape, Rectangle.class)).willReturn(true);

        // When
        createGameEngine().identifyCurrentShape(Triangle.class);

        // Then
        verify(audioEffectEngine).play(AudioEffects.AUDIO_EFFECT_ID_FAILURE);
    }

    private GameEngine createGameEngine() {
        return new GameEngine(shapeIdentifier, shapeGenerator, audioEffectEngine);
    }
}
