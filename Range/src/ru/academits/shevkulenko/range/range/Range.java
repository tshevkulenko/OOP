package ru.academits.shevkulenko.range.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public boolean hasCrossing(Range range) {
        return this.from < range.to && this.to > range.from;
    }

    public Range getCross(Range range) {
        if (hasCrossing(range)) {
            return new Range(Math.max(this.from, range.from), Math.min(this.to, range.to));
        } else {
            return null;
        }
    }

    public Range[] getUnion(Range range) {
        if (hasCrossing(range)) {
            return new Range[]{new Range(Math.min(this.from, range.from), Math.max(this.to, range.to))};
        } else {
            return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
        }
    }

    public Range[] getDifference(Range range) {
        if (hasCrossing(range)) {
            if (this.from < range.from) {
                return new Range[]{new Range(this.from, range.from)};
            } else {
                return new Range[]{new Range(range.to, this.to)};
            }
        }

        if (this.from >= range.from && this.to <= range.to) {
            return new Range[]{};
        }

        if (this.from < range.from && this.to > range.to) {
            return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
        }

        return new Range[]{new Range(this.from, this.to)};
    }
}