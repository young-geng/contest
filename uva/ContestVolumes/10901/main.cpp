#include <vector>
#include <stdlib.h>
#include <iostream>
#include <limits.h>

#define leftFirstTime cars[left[leftCount]].arriveTime
#define rightFirstTime cars[right[rightCount]].arriveTime

using namespace std;

enum Direction { LEFT, RIGHT };

class Car {
public:
    int arriveTime;
    Direction direction;
    int crossTime;
};



Direction reverse(Direction direc) {
    return direc == LEFT ? RIGHT : LEFT;
}

bool hasCar(int carCount, vector<int> cars) {
    return carCount < cars.size();
}



int minArriveTime(int leftCount, int rightCount, vector<int> left, vector<int> right, Car cars[]) {
    if (!hasCar(leftCount, left) && !hasCar(rightCount, right)) 
        return -1;
    if (hasCar(leftCount, left) && hasCar(rightCount, right))
        return min<int>(leftFirstTime, rightFirstTime);
    if (hasCar(leftCount, left))
        return leftFirstTime;
    return rightFirstTime;
}

void runTestCase() {
    int load, crossTime, carCount;
    cin >> load;
    cin >> crossTime;
    cin >> carCount;
    Car cars[carCount];
    vector<int> left, right;
    int leftCount = 0, rightCount = 0;
    for (int i = 0; i < carCount; i++) {
        cin >> cars[i].arriveTime;
        string s;
        cin >> s;
        if (s.compare("left")) {
            cars[i].direction = RIGHT;
            right.push_back(i);
        } else {
            cars[i].direction = LEFT;
            left.push_back(i);
        }
    }
    if (carCount == 0) {
        cout << endl;
        return;
    }
    int maxTime = cars[carCount - 1].arriveTime;
    Direction currentDirection = LEFT;
    int timeCounter = 0;
    while (hasCar(leftCount, left) || hasCar(rightCount, right)) {
        if (currentDirection == LEFT) {
            timeCounter = max<int>(timeCounter, minArriveTime(leftCount, rightCount, left, right, cars));
            for (int i = 0; i < load 
                && hasCar(leftCount, left)
                && leftFirstTime <= timeCounter; i++, leftCount++) 
            {
                cars[left[leftCount]].crossTime = timeCounter + crossTime;
            }
        } else {
            timeCounter = max<int>(timeCounter, minArriveTime(leftCount, rightCount, left, right, cars));
            for (int i = 0; i < load 
                && hasCar(rightCount, right)
                && rightFirstTime <= timeCounter; i++, rightCount++) 
            {
                cars[right[rightCount]].crossTime = timeCounter + crossTime;
            }
        }
        timeCounter += crossTime;
        currentDirection = reverse(currentDirection);
    }
    for (int i = 0; i < carCount; i++) {
        cout << cars[i].crossTime << endl;
    }
    cout << endl;
    return;
}


int main(int argc, char** argv) {
    int totalCases;
    cin >> totalCases;
    for (int i = 0; i < totalCases; i++) {
        runTestCase();
    }
    return 0;
}
         
  
