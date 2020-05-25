package model.structure;

public class Building {
    private Double surface;
    private Integer height;
    private String architecture;

    public Building(Double surface, Integer height, String architecture) {
        this.surface = surface;
        this.height = height;
        this.architecture = architecture;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }
}
