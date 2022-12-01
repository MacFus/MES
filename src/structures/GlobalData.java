package structures;

import loadfile.ReadFile;

public class GlobalData {
    private int simulationTime;
    private int simulationStepTime;
    private int conductivity;
    private int alfa;
    private int tot;
    private int initialTemp;
    private int density;
    private int specificHeat;
    private int numberOfNodes;
    private int numberOfElements;
    private Grid grid = new Grid();


    private String[] fileContent = ReadFile.readFileContent();

    public int getSimulationTime() {
        return simulationTime;
    }

    public GlobalData() {
        setSimulationTime();
        setSimulationStepTime();
        setConductivity();
        setAlfa();
        setTot();
        setInitialTemp();
        setDensity();
        setSpecificHeat();
        setNumberOfNodes();
        setNumberOfElements();
    //    setGrid();
    }

//    public void setGrid() {
//        Grid grid = new Grid();
//        this.grid = grid;
//    }

    public void setSimulationTime() {
        for (String line : fileContent) {
            if (line.contains("SimulationTime")) {
                String[] splitString = line.split(" ");
                this.simulationTime = Integer.parseInt(splitString[1]);
            }
        }
    }

    public int getSimulationStepTime() {
        return simulationStepTime;
    }

    public void setSimulationStepTime() {
        for (String line : fileContent) {
            if (line.contains("SimulationStepTime")) {
                String[] splitString = line.split(" ");
                this.simulationStepTime = Integer.parseInt(splitString[1]);
            }
        }
    }

    public int getConductivity() {
        return conductivity;
    }

    public void setConductivity() {
        for (String line : fileContent) {
            if (line.contains("Conductivity")) {
                String[] splitString = line.split(" ");
                this.conductivity = Integer.parseInt(splitString[1]);
            }
        }
    }

    public int getAlfa() {
        return alfa;
    }

    public void setAlfa() {
        for (String line : fileContent) {
            if (line.contains("Alfa")) {
                String[] splitString = line.split(" ");
                this.alfa = Integer.parseInt(splitString[1]);
            }
        }
    }

    public int getTot() {
        return tot;
    }

    public void setTot() {
        for (String line : fileContent) {
            if (line.contains("Tot")) {
                String[] splitString = line.split(" ");
                this.tot = Integer.parseInt(splitString[1]);
            }
        }
    }

    public int getInitialTemp() {
        return initialTemp;
    }

    public void setInitialTemp() {
        for (String line : fileContent) {
            if (line.contains("InitialTemp")) {
                String[] splitString = line.split(" ");
                this.initialTemp = Integer.parseInt(splitString[1]);
            }
        }
    }

    public int getDensity() {
        return density;
    }

    public void setDensity() {
        for (String line : fileContent) {
            if (line.contains("Density")) {
                String[] splitString = line.split(" ");
                this.density = Integer.parseInt(splitString[1]);
            }
        }
    }

    public int getSpecificHeat() {
        return specificHeat;
    }

    public void setSpecificHeat() {
        for (String line : fileContent) {
            if (line.contains("SpecificHeat")) {
                String[] splitString = line.split(" ");
                this.specificHeat = Integer.parseInt(splitString[1]);
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes() {
        for (String line : fileContent) {
            if (line.contains("Nodes number")) {
                String[] splitString = line.split(" ");
                this.numberOfNodes = Integer.parseInt(splitString[2]);
            }
        }
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements() {
        for (String line : fileContent) {
            if (line.contains("Elements number")) {
                String[] splitString = line.split(" ");
                this.numberOfElements = Integer.parseInt(splitString[2]);
            }
        }
    }

    @Override
    public String toString() {
        return  "\nGlobalVariables{" +
                "\n simulationTime=" + simulationTime +
                "\n simulationStepTime=" + simulationStepTime +
                "\n conductivity=" + conductivity +
                "\n alfa=" + alfa +
                "\n tot=" + tot +
                "\n initialTemp=" + initialTemp +
                "\n density=" + density +
                "\n specificHeat=" + specificHeat +
                "\n numberOfNodes=" + numberOfNodes +
                "\n numberOfElements=" + numberOfElements +
                "\n grid=" + grid +
                '}';
    }

    public static void main(String[] args) {
        GlobalData globalData = new GlobalData();
        System.out.println(globalData);
    }
}
