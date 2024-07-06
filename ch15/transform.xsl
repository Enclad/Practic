<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Orang</title>>
            </head>
            <body>
                <h1>Orang</h1>
                <table>
                    <tr>
                        <th>Название</th>
                        <th>Почва</th>
                        <th>Происхождение</th>
                        <th>Цвет стебля</th>
                        <th>Цвеи листьев</th>
                        <th>Средний размер листьев</th>
                        <th>Оптимальная для роста температура (градусы)</th>
                        <th>Светолюбивость</th>
                        <th>Необходимый полив (мл/неделя)</th>
                        <th>Способ размножения</th>
                    </tr>
                    <xsl:for-each select="//flower">
                        <xsl:sort select="growing_tips/temperature" data-type="number"/>
                        <tr>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="soil"/>
                            </td>
                            <td>
                                <xsl:value-of select="origin"/>
                            </td>
                            <td>
                                <xsl:value-of select="visual_parameters/color_stick"/>
                            </td>
                            <td>
                                <xsl:value-of select="visual_parameters/color_paper"/>
                            </td>
                            <td>
                                <xsl:value-of select="visual_parameters/average_size"/>
                            </td>
                            <td>
                                <xsl:value-of select="growing_tips/temperature"/>
                            </td>
                            <td>
                                <xsl:value-of select="growing_tips/light"/>
                            </td>
                            <td>
                                <xsl:value-of select="growing_tips/wosher"/>
                            </td>
                            <td>
                                <xsl:value-of select="multiplying"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>