javac -cp "$(pwd)/dependencies:commons-math3-3.0.jar:$(pwd)/dependencies/cplex.jar:$(pwd)/dependencies/guava-19.0.jar:$(pwd)/dependencies/kryonet-2.21-all.jar:$(pwd)/dependencies/adx.jar:./:$(pwd)/src/" src/adx/server/*.java
javac -cp "$(pwd)/dependencies:commons-math3-3.0.jar:$(pwd)/dependencies/cplex.jar:$(pwd)/dependencies/guava-19.0.jar:$(pwd)/dependencies/kryonet-2.21-all.jar:$(pwd)/dependencies/adx.jar:./:$(pwd)/src/" src/adx/variants/thirtydaysgame/*.java
javac -cp "$(pwd)/dependencies:commons-math3-3.0.jar:$(pwd)/dependencies/cplex.jar:$(pwd)/dependencies/guava-19.0.jar:$(pwd)/dependencies/kryonet-2.21-all.jar:$(pwd)/dependencies/adx.jar:./:$(pwd)/src/" src/adx/variants/onedaygame/*.java
javac -cp "$(pwd)/dependencies:commons-math3-3.0.jar:$(pwd)/dependencies/cplex.jar:$(pwd)/dependencies/guava-19.0.jar:$(pwd)/dependencies/kryonet-2.21-all.jar:$(pwd)/dependencies/adx.jar:./:$(pwd)/src/" src/adx/variants/twodaysgame/*.java


java -cp "$(pwd)/dependencies:commons-math3-3.0.jar:$(pwd)/dependencies/cplex.jar:$(pwd)/dependencies/guava-19.0.jar:$(pwd)/dependencies/kryonet-2.21-all.jar:$(pwd)/dependencies/adx.jar:./:$(pwd)/src/" adx.variants.thirtydaysgame.ThirtyDaysThirtyCampaignsGameServer

