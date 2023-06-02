package dev.elliotjarnit.ElliotEngine.Objects;

import dev.elliotjarnit.ElliotEngine.Graphics.Color;
import dev.elliotjarnit.ElliotEngine.Utils.Vector3;

public class ETetrahedron extends EObject {
    private double width;
    private double height;
    private Color color;

    public ETetrahedron() {
        super();
        this.width = 100;
        this.height = 100;
        this.color = Color.WHITE;
        calculateFaces();
    }
    public ETetrahedron(Vector3 origin) {
        super(origin);
        this.width = 100;
        this.height = 100;
        this.color = Color.WHITE;
        calculateFaces();
    }
    public ETetrahedron(Vector3 origin, double width, double height) {
        super(origin);
        this.width = width;
        this.height = height;
        this.color = Color.WHITE;
        calculateFaces();
    }
    public ETetrahedron(Vector3 origin, double width, double height, Color color) {
        super(origin);
        this.width = width;
        this.height = height;
        this.color = color;
        calculateFaces();
    }
    public ETetrahedron(Vector3 origin, Color color) {
        super(origin);
        this.width = 100;
        this.height = 100;
        this.color = color;
        calculateFaces();
    }



    private void calculateFaces() {
        Vector3 origin = new Vector3(0, 0, 0);
        EFace[] faces = new EFace[4];

        // Origin is center of bottom face

        Vector3 top = new Vector3(origin.x, origin.y + height, origin.z);
        Vector3 frontLeft = new Vector3(origin.x - width / 2, origin.y, origin.z + width / 2);
        Vector3 frontRight = new Vector3(origin.x + width / 2, origin.y, origin.z + width / 2);
        Vector3 backLeft = new Vector3(origin.x - width / 2, origin.y, origin.z - width / 2);

        faces[0] = new EFace(frontLeft, frontRight, top, color);
        faces[1] = new EFace(frontRight, backLeft, top, color);
        faces[2] = new EFace(backLeft, frontLeft, top, color);
        faces[3] = new EFace(frontLeft, frontRight, backLeft, color);

        this.setFaces(faces);
    }


    @Override
    public void update() {}

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
        calculateFaces();
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
        calculateFaces();
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
        calculateFaces();
    }
}
