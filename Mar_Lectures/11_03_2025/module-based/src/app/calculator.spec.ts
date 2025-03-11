import { Calculator } from "./calculator";

describe('Class Calculator', () => {
    let calc: Calculator;
    beforeEach( ()=> {
        calc = new Calculator();
    })
    it('Addition testing', () => {
        let result = calc.add(11,22);
        expect(result).toBe(33);
    })

    it('Subtraction testing', () => {
        let result = calc.sub(55,22);
        expect(result).toBe(33);
    })

})