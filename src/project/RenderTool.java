package project;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector2f;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class RenderTool {

    public static void traceRender(ArrayList<Vector2f>  points,float partSize){
        float[] coordinateArray = new float[points.size()*2];
        int i = 0;
        for(Vector2f tr:points){
            coordinateArray[i] = tr.x;
            i++;
            coordinateArray[i] = tr.y;
            i++;
        }

        FloatBuffer buffer = BufferUtils.createFloatBuffer(coordinateArray.length);
        buffer.put(coordinateArray);
        buffer.flip();

        int vboID = GL15.glGenBuffers();
        int vaoID = GL30.glGenVertexArrays();

        GL30.glBindVertexArray(vaoID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);

        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0,3,GL11.GL_FLOAT,false,0, 0);


        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);


        // Bind to the VAO that has all the information about the quad vertices
        GL30.glBindVertexArray(vaoID);
        GL20.glEnableVertexAttribArray(0);

        // Draw the vertices
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPointSize(partSize);
        GL11.glDrawArrays(GL11.GL_POINT, 0, points.size());

        // Put everything back to default (deselect)
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);




    }
}
