import gurobi.*;
/**
 * Created by Eric on 6/22/2014.
 */
public class Main{
    public static void main(String[] args){
        GRBEnv env = null;
        try {
            env = new GRBEnv();
            GRBModel model = new GRBModel(env);

            //decision variables
            //upper bound, lowerbound, constant, variable type, name
            GRBVar x1 = model.addVar(0, GRB.INFINITY, 20, GRB.CONTINUOUS, "food.1");
            GRBVar x2 = model.addVar(0, GRB.INFINITY, 10, GRB.CONTINUOUS, "food.2");
            GRBVar x3 = model.addVar(0, GRB.INFINITY, 31, GRB.CONTINUOUS, "food.3");
            GRBVar x4 = model.addVar(0, GRB.INFINITY, 11, GRB.CONTINUOUS, "food.4");
            GRBVar x5 = model.addVar(0, GRB.INFINITY, 12, GRB.CONTINUOUS, "food.5");

            //update model with decision variables
            model.update();

            //ad constraints
            GRBLinExpr linExp1 = new GRBLinExpr();
            linExp1.addTerm(2, x1);
            linExp1.addTerm(3, x3);
            linExp1.addTerm(1, x4);
            linExp1.addTerm(2, x5);
            GRBConstr con1 = model.addConstr(linExp1, GRB.GREATER_EQUAL, 21, "nutrient.1");

            GRBLinExpr linExp2 = new GRBLinExpr();
            linExp2.addTerm(1, x2);
            linExp2.addTerm(2, x3);
            linExp2.addTerm(2, x4);
            linExp2.addTerm(1, x5);
            GRBConstr con2 = model.addConstr(linExp2, GRB.GREATER_EQUAL, 12, "nutrient.2");

//            model.update();

//            System.out.println(x1.get(GRB.DoubleAttr.X));
//            System.out.println(x2.get(GRB.DoubleAttr.X));
//            System.out.println(x3.get(GRB.DoubleAttr.X));
//            System.out.println(x4.get(GRB.DoubleAttr.X));
//            System.out.println(x5.get(GRB.DoubleAttr.X));
//
//            System.out.println((x4.get(GRB.DoubleAttr.X) + (2 * x5.get(GRB.DoubleAttr.X))));
//            System.out.println(( 2 * x4.get(GRB.DoubleAttr.X) + x5.get(GRB.DoubleAttr.X)));

            //How would optimal cost change if we were required to consume at least one unit of food type 1
//            x1.set(GRB.DoubleAttr.LB, 1);
//            System.out.println(x1.get(GRB.DoubleAttr.RC));
//            model.optimize();
//
//            //reset
//            x1.set(GRB.DoubleAttr.LB, 0);
//
//            //How would optimal cost chnge if wer were required to consume an extra ounce of nutrient #1
//            con1.set(GRB.DoubleAttr.RHS, 22);
//            System.out.print(con1.get(GRB.DoubleAttr.Pi));
//            model.optimize();
//
//            //reset
//            con1.set(GRB.DoubleAttr.RHS, 21);
//            model.optimize();

            //change so that we want a diet that includes at least 2 ounces of at least 3 different food
            GRBVar y1 = model.addVar(0, 1, 1, GRB.BINARY, "bin.1");
            GRBVar y2 = model.addVar(0, 1, 1, GRB.BINARY, "bin.2");
            GRBVar y3 = model.addVar(0, 1, 1, GRB.BINARY, "bin.3");
            GRBVar y4 = model.addVar(0, 1, 1, GRB.BINARY, "bin.4");
            GRBVar y5 = model.addVar(0, 1, 1, GRB.BINARY, "bin.5");

            model.update();

            GRBLinExpr linExpr3 = new GRBLinExpr();
            linExpr3.addTerm(2, y1);
            GRBLinExpr linExpr3b = new GRBLinExpr();
            linExpr3b.addTerm(1, x1);
            GRBLinExpr linExpr4 = new GRBLinExpr();
            linExpr4.addTerm(2, y2);
            GRBLinExpr linExpr4b = new GRBLinExpr();
            linExpr4b.addTerm(1, x2);
            GRBLinExpr linExpr5 = new GRBLinExpr();
            linExpr5.addTerm(2, y3);
            GRBLinExpr linExpr5b = new GRBLinExpr();
            linExpr5b.addTerm(1, x3);
            GRBLinExpr linExpr6 = new GRBLinExpr();
            linExpr6.addTerm(2, y4);
            GRBLinExpr linExpr6b = new GRBLinExpr();
            linExpr6b.addTerm(1, x4);
            GRBLinExpr linExpr7 = new GRBLinExpr();
            linExpr7.addTerm(2, y5);
            GRBLinExpr linExpr7b = new GRBLinExpr();
            linExpr7b.addTerm(1, x5);
            GRBLinExpr linExp8 = new GRBLinExpr();
            linExp8.addTerm(1,y1);
            linExp8.addTerm(1,y2);
            linExp8.addTerm(1,y3);
            linExp8.addTerm(1,y4);
            linExp8.addTerm(1,y5);

            GRBConstr con3 = model.addConstr(x1, GRB.GREATER_EQUAL, linExpr3, "test1");
            GRBConstr con4 = model.addConstr(x2, GRB.GREATER_EQUAL, linExpr4, "test2");
            GRBConstr con5 = model.addConstr(x3, GRB.GREATER_EQUAL, linExpr5, "test3");
            GRBConstr con6 = model.addConstr(x4, GRB.GREATER_EQUAL, linExpr6, "test4");
            GRBConstr con7 = model.addConstr(x5, GRB.GREATER_EQUAL, linExpr7, "test5");
            GRBConstr con8 = model.addConstr(linExp8, GRB.GREATER_EQUAL, 3, "6");

            model.optimize();

            System.out.println(x1.get(GRB.DoubleAttr.X));
            System.out.println(x2.get(GRB.DoubleAttr.X));
            System.out.println(x3.get(GRB.DoubleAttr.X));
            System.out.println(x4.get(GRB.DoubleAttr.X));
            System.out.println(x5.get(GRB.DoubleAttr.X));

        } catch (GRBException e) {
            e.printStackTrace();
        }


    }

    static void testNewModel(){
        GRBEnv env = null;
        try {
            env = new GRBEnv();
            GRBModel model = new GRBModel(env);
            model.read("diet.bas");
            GRBVar x1 = model.getVarByName("food.1");
            System.out.println(x1);
        } catch (GRBException e) {
            e.printStackTrace();
        }

    }
}
