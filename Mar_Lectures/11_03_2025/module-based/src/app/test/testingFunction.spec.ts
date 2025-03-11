import { addition } from "./testingFunction"

describe("Calculation testing", () => {
    it("Testing add function", () => {
        expect(addition(11,22)).toBe(33);
    })
})

describe("string testing", () => {
    let i=0;

    beforeEach(() => {
        console.log("Before each Test case no: " + ++i);
    })

    afterEach(() => {
        console.log("After each Test case no: " + i);
    })

    beforeAll(() => {
        console.log("-------STARTED-------");
    })

    afterAll(() => {
        console.log("-------FINISHED-------");
    })

    it("Testing string equality using 'toBe'", () => {
        let str = "Hello Everybody";
        expect(str).toBe("Hello Everybody");
    })
    it("Testing string equality using 'toEqual'", () => {
        let str = "Hello Everybody";
        expect(str).toEqual("Hello Everybody");
    })

    it("Testing string using Regex", () => {
        let str = "Happy New Year 2025";
        expect(str).toMatch(/\d+/);
    })
})

describe("Testing JSON/Array Object", () => {
    it("Testing with deep checking", () => {
        let e1 = {"name":"Raju","age": 25};
        expect(e1).toEqual({"name":"Raju","age": 25});
    })

    it("Testing array", () => {
        let arr = [11,22,33,44,55];
        expect(arr).toEqual([11,22,33,44,55]);
    })
})