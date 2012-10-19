<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml" indent="yes" />
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-portrait"
                                       page-height="29.7cm" page-width="21.0cm" margin="2cm">
                    <fo:region-body margin="1cm 0cm" />
                    <fo:region-before extent="0.5cm" />
                    <fo:region-after extent="0.5cm" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4-portrait">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block text-align="center" font-style="italic">
                        <xsl:value-of select="root/captions/project" />
                        <xsl:text> - </xsl:text>
                        <xsl:value-of select="root/project/name" />
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block text-align="center" font-style="italic">
                        <xsl:value-of select="root/captions/page" />
                        <xsl:text> </xsl:text>
                        <fo:page-number />
                        <xsl:text> </xsl:text>
                        <xsl:value-of select="root/captions/of" />
                        <xsl:text> </xsl:text>
                        <fo:page-number-citation ref-id="last-page" />
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <xsl:for-each select="root/definitions/definition">
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="/root/captions/classification" />
                                <xsl:text>: </xsl:text>
                            </fo:inline>
                            <xsl:value-of select="classification" />
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="/root/captions/category" />
                                <xsl:text>: </xsl:text>
                            </fo:inline>
                            <xsl:value-of select="category" />
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">
                                <xsl:choose>
                                    <xsl:when test="count(symbols/symbol) != 1">
                                        <xsl:value-of select="/root/captions/symbols" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="/root/captions/symbol" />
                                    </xsl:otherwise>
                                </xsl:choose>
                                <xsl:text>: </xsl:text>
                            </fo:inline>
                            <xsl:for-each select="symbols/symbol">
                                <xsl:value-of select="name" />
                                <xsl:if test="position() != last()">
                                    <xsl:text>, </xsl:text>
                                </xsl:if>
                            </xsl:for-each>
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="/root/captions/notion" />
                                <xsl:text>: </xsl:text>
                            </fo:inline>
                            <xsl:value-of select="notion" />
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="/root/captions/actualIntention" />
                                <xsl:text>: </xsl:text>
                            </fo:inline>
                            <xsl:value-of select="actualIntention" />
                        </fo:block>
                        <fo:block space-after="0.5cm">
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="/root/captions/futureIntention" />
                                <xsl:text>: </xsl:text>
                            </fo:inline>
                            <xsl:value-of select="futureIntention" />
                        </fo:block>
                        <fo:block space-after="1.0cm">
                            <xsl:if test="comments">
                                <fo:block font-weight="bold" space-after="0.5cm">
                                    <xsl:value-of select="/root/captions/comments" />
                                    <xsl:text>: </xsl:text>
                                </fo:block>
                                <xsl:for-each select="comments/comment">
                                    <fo:block space-after="0.5cm">
                                        <fo:inline font-style="italic">
                                            <xsl:value-of select="date" />
                                            <xsl:text> - </xsl:text>
                                            <xsl:value-of select="user" />
                                            <xsl:text>: </xsl:text>
                                        </fo:inline>
                                        <xsl:value-of select="content" />
                                    </fo:block>
                                </xsl:for-each>
                            </xsl:if>
                        </fo:block>
                        <fo:block space-after="1.0cm" border-top="0.05cm groove #ccc"></fo:block>
                    </xsl:for-each>
                    <fo:block id="last-page">
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>