const {asyncQueryMethod} = require("./config");
const {Professional} = require("./models/professional");
const {mongoose_connection} = require("./config");

const mongoose = mongoose_connection();

asyncQueryMethod("SELECT * FROM professionals")
.then((res) => {
    for (const prof of res) {
        const firstName = prof.firstName;
        const lastName = prof.lastName;
        const schoolEmail = prof.schoolEmail;
        const workEmail = prof.workEmail;
        const jobTitle = prof.jobTitle;
        const company = prof.company;
        const schoolName = prof.schoolName;

        const professional = new Professional({firstName, lastName, schoolEmail, schoolName, workEmail, jobTitle, company});
        professional.save((err, p) => {
            if(err) {
                console.log(err);
            } else {
                console.log(professional.firstName + 'saved!');
            }
        })
    }
})
.catch((err) => {
    console.log(err);
})