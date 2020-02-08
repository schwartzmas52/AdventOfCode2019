const fs = require('fs');
const async = require('async');

fs.readFile('./Day1.txt', 'utf8', function(err, contents) {
    if (err) {
        console.log(err);
        return;
    }

    const values = contents.split("\n");
    var total = 0;

    async.eachSeries(values, function(value, valueCb) {
        var currentValue = value;
        if (value) {
            currentValue = (Math.floor(value / 3) - 2);
        }
        else {
            return valueCb()
        }
        async.whilst(function(cb) {
            return cb(null, currentValue > 0);
        }, function(cb) {
            total += currentValue;
            currentValue = (Math.floor(currentValue / 3) - 2);
            return process.nextTick(cb);
        }, function(err) {
            if (err) {
                return valueCb(err);
            }
            return valueCb();
        })
    }, function(err) {
        if (err) {
            console.log(err);
            return;
        }

        console.log(total);
    });
});
