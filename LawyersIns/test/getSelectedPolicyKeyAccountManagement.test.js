const fs = require("fs");
const vm = require("vm");
const assert = require("assert");

const source = fs.readFileSync("web/script/lawyers.js", "utf8");
const start = source.indexOf("function getSelectedPolicyKeyAccountManagement");
const end = source.indexOf("function getSelectedOutputColumns", start);
const elements = {
	SearchlistSize: { value: 3 },
	AccountID_0: { checked: true, value: "P1" },
	AccountID_1: { checked: false, value: "P2" },
	AccountID_2: { checked: true, value: "P3" },
	strPolicyKey: { value: "" }
};
const context = { document: { getElementById: id => elements[id] || null } };

vm.runInNewContext(source.slice(start, end) + ";getSelectedPolicyKeyAccountManagement();", context);
assert.strictEqual(elements.strPolicyKey.value, "P1,P3");

context.selectAllAccountManagement(true);
assert.strictEqual(elements.strPolicyKey.value, "P1,P2,P3");
context.selectAllAccountManagement(false);
assert.strictEqual(elements.strPolicyKey.value, "");
